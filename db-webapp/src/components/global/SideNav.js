import React from 'react';
import { Sidebar, Menu, MenuItem, SubMenu, useProSidebar } from 'react-pro-sidebar';
import { Home, PieChart, Book, CalendarToday, Menu as MenuIcon } from '@mui/icons-material';
import { IconButton } from '@mui/material';
import { Box } from '@mui/system';

const SideBar = () => {
    const { collapseSidebar } = useProSidebar();

    const handleCollapse = () => {
        collapseSidebar();
    };

    return (
        <Sidebar>
            <Box display="flex" alignItems="center" justifyContent="center" p={2}>
                <IconButton onClick={handleCollapse} color="inherit">
                    <MenuIcon />
                </IconButton>
            </Box>
            <Menu iconShape="square">
                <MenuItem icon={<Home />}>
                    Dashboard
                </MenuItem>
                <SubMenu title="Charts" icon={<PieChart />}>
                    <MenuItem>
                        Pie charts
                    </MenuItem>
                    <MenuItem>
                        Line charts
                    </MenuItem>
                </SubMenu>
                <MenuItem icon={<Book />}>
                    Documentation
                </MenuItem>
                <MenuItem icon={<CalendarToday />}>
                    Calendar
                </MenuItem>
            </Menu>
        </Sidebar>
    );
};

export default SideBar;
