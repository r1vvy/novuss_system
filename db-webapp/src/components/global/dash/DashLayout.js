import { Outlet } from 'react-router-dom';
import DashHeader from "./DashHeader";
import DashFooter from "./DashFooter";
import SideNav from "../SideNav";
import { ProSidebarProvider } from "react-pro-sidebar";
import { Box } from '@mui/system';

const DashLayout = () => {
    return (
        <Box display="flex" flexDirection="column" minHeight="100vh">
            <ProSidebarProvider>
                <DashHeader />
                <Box display="flex" flex="1">
                    <Box position="relative" flex="0 0 auto">
                        <SideNav />
                    </Box>
                    <Box flex="1" sx={{ overflow: 'auto', position: 'relative', minHeight: 0 }}>
                        <div className="dash-container" style={{ padding: '16px' }}>
                            <Outlet />
                        </div>
                    </Box>
                </Box>
                <DashFooter />
            </ProSidebarProvider>
        </Box>
    );
};

export default DashLayout;
