import { Outlet } from 'react-router-dom';
import DashHeader from "./DashHeader";
import SideNav from "../SideNav";
import { ProSidebarProvider } from "react-pro-sidebar";
import { Box } from '@mui/system';

const DashLayout = () => {
    return (
        <Box minHeight="100vh" display="flex" flexDirection="column">
            <ProSidebarProvider>
                <DashHeader />
                <Box flex="1" display="flex" overflow="hidden">
                    <SideNav />
                    <Box flex="1" overflow="auto">
                        <div className="dash-container" style={{ padding: '16px' }}>
                            <Outlet />
                        </div>
                    </Box>
                </Box>
            </ProSidebarProvider>
        </Box>
    );
};

export default DashLayout;
