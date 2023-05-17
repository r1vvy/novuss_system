import { Link } from "react-router-dom";

const DashHeader = () => {
    const content = (
        <header>
            <div className="dash-header-container">
                <Link to="/dash">
                    <h1 className="dash-header-title">LNF</h1>
                </Link>
                <nav className="dash-header-nav">
                </nav>
            </div>
        </header>
    )
    return content
}
export default DashHeader
