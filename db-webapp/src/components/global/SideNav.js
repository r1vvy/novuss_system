import React, { useState } from 'react';
import { Sidebar, Menu, MenuItem, SubMenu, useProSidebar } from 'react-pro-sidebar';
import {AssignmentInd, Dashboard, ExitToApp, FolderShared, Home, Menu as MenuIcon} from '@mui/icons-material';
import { Divider, IconButton } from '@mui/material';
import { Box } from '@mui/system';
import { useTheme } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';
import GroupIcon from '@mui/icons-material/Group';
import { ROLES } from '../../app/roles';
import AuthService from "../../services/AuthService";
import WcIcon from '@mui/icons-material/Wc';
import SportsIcon from '@mui/icons-material/Sports';
import EmojiEventsIcon from '@mui/icons-material/EmojiEvents';

const CustomMenuItem = ({ icon, name, selected, onClick, path }) => {
    const theme = useTheme();
    const navigate = useNavigate();

    const handleClick = () => {
        onClick();
        navigate(path);
    };

    return (
        <MenuItem
            icon={icon}
            active={selected}
            onClick={handleClick}
            style={selected ? { backgroundColor: theme.palette.secondary.main } : {}}
        >
            {name}
        </MenuItem>
    );
};

const SideBar = () => {
    const { collapseSidebar } = useProSidebar();

    const [selectedItem, setSelectedItem] = useState('dashboard');
    const userRoles = AuthService.getUserRoles();

    const handleCollapse = () => {
        collapseSidebar();
    };

    const handleItemClick = (item) => {
        setSelectedItem(item);
    };

    const renderMenuItems = () => {
        const menuItems = [
            {
                icon: <Dashboard />,
                name: "Vadības panelis",
                id: "dashboard",
                path: "/dash"
            },
            {
                icon: <EmojiEventsIcon />,
                name: "Sacensības",
                id: "competitions",
                path: "competitions"
            },
            {
                icon: <GroupIcon />,
                name: "Lietotāji",
                id: "users",
                path: "users",
                allowedRoles: [ROLES.Admin, ROLES.SuperAdmin]
            },
            {
                icon: <WcIcon />,
                name: "Spēlētāji",
                id: "players",
                path: "players",
                allowedRoles: [ROLES.SuperAdmin, ROLES.Admin, ROLES.EventManager, ROLES.User]
            },
            {
                icon: <FolderShared />,
                name: "Klubi",
                id: "clubs",
                path: "clubs",
                allowedRoles: [ROLES.SuperAdmin, ROLES.Admin, ROLES.EventManager, ROLES.User]
            },
            {
                icon: <SportsIcon />,
                name: "Tiesneši",
                id: "referees",
                path: "referees",
                allowedRoles: [ROLES.SuperAdmin, ROLES.Admin, ROLES.EventManager, ROLES.User],
            },
            {
                icon: <AssignmentInd />,
                name: "Personu dati",
                id: "people",
                path: "people",
                allowedRoles: [ROLES.Admin, ROLES.SuperAdmin]
            },
            {
                icon: <ExitToApp />,
                name: "Izrakstīties",
                id: "logout",
                path: "/logout"
            },
        ];

        const allowedMenuItems = menuItems.filter(item =>
            item.allowedRoles ? item.allowedRoles.some(role => userRoles.includes(role)) : true
        );

        return allowedMenuItems.map(item => (
            <div key={item.id}>
                <CustomMenuItem
                    icon={item.icon}
                    name={item.name}
                    selected={selectedItem === item.id}
                    onClick={() => handleItemClick(item.id)}
                    path={item.path}
                />
            </div>
        ))
    };

    return (
        <Sidebar>
            <Box display="flex" alignItems="center" justifyContent="center" p={2}>
                <IconButton onClick={handleCollapse} color="inherit">
                    <MenuIcon />
                </IconButton>
            </Box>
            <Menu iconShape="square">
                {renderMenuItems()}
            </Menu>
        </Sidebar>
    );
};

export default SideBar;
