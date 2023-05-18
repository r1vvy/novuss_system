import React, { useState } from 'react';
import { Sidebar, Menu, MenuItem, SubMenu, useProSidebar } from 'react-pro-sidebar';
import {Dashboard, ExitToApp, Home, Menu as MenuIcon} from '@mui/icons-material';
import { IconButton } from '@mui/material';
import { Box } from '@mui/system';
import { useTheme } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';
import GroupIcon from '@mui/icons-material/Group';
import useLogout from "../../hooks/useLogout";

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

    const [selectedItem, setSelectedItem] = useState('dashboard'); // Set the initially selected item here

    const handleCollapse = () => {
        collapseSidebar();
    };

    const handleItemClick = (item) => {
        setSelectedItem(item);
    };

    return (
        <Sidebar>
            <Box display="flex" alignItems="center" justifyContent="center" p={2}>
                <IconButton onClick={handleCollapse} color="inherit">
                    <MenuIcon />
                </IconButton>
            </Box>
            <Menu iconShape="square">
                <CustomMenuItem
                    icon={<Dashboard />}
                    name="Vadības panelis"
                    selected={selectedItem === 'dashboard'}
                    onClick={() => handleItemClick('dashboard')}
                    path="/dash"
                />
                <CustomMenuItem
                    icon={<GroupIcon />}
                    name="Lietotāji"
                    selected={selectedItem === 'users'}
                    onClick={() => handleItemClick('users')}
                    path="/dash/users"
                />
                <CustomMenuItem
                    icon={<ExitToApp />}
                    name="Izrakstīties"
                    selected={selectedItem === 'logout'}
                    onClick={() => handleItemClick('logout')}
                    path="/logout"
                />
            </Menu>
        </Sidebar>
    );
};

export default SideBar;
