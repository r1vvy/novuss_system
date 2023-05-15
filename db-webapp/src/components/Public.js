import { Link } from "react-router-dom"

const Public = () => {

    const content = (
        <section className="public">
            <header>
                <h1>Welcome to LNF Management website!</h1>
            </header>
            <main>
                <p>Lorem Ipsum</p>
                <p>&nbsp;</p>
                <address>
                    Lorem Ipsum<br />
                    Lorem Ipsum<br />
                    Lorem Ipsum<br />
                    <a href="tel:+15555555555">26262626</a>
                </address>
            </main>
            <footer>
                <Link to="/login">Authorized personnel only</Link>
            </footer>
        </section>

    )
    return content
}
export default Public