import React from 'react';
import { Link } from 'react-router-dom';

const SideNav = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="collapse navbar-collapse">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        {/* add logout button here */}
                        <Link className="nav-link" to="/logout">
                            Logout
                        </Link>
                    </li>
                    {/* Add more navigation items here */}
                </ul>
            </div>
        </nav>
    );
};

export default SideNav;
