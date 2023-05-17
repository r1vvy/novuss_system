import { Outlet } from 'react-router-dom';
import DashHeader from "./DashHeader";
import DashFooter from "./DashFooter";
import SideNav from "../SideNav";
const DashLayout = () => {
    return (
        <>
            <DashHeader />
            <SideNav />
            <div className="dash-container">
                <Outlet />
            </div>
            <DashFooter />
        </>
    );
};

export default DashLayout;