import {Link} from "react-router-dom";
import styled from "styled-components";

const Navbar = styled.header`
    padding: 8px 16px;
    box-shadow: 0 1px 2px grey;
    display: flex;
    justify-content: space-between;
    align-items: center;
`

const LogoWrapper = styled.div`
    background-color: red;
    width: 120px;
    height: 48px;
`

const Navigation = styled.nav`
    ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    
    a {
        padding: 8px;
    }
`

export default function TopBar() {
    return (
        <Navbar>
            <LogoWrapper />

            <Navigation>
                <ul>
                    <li>
                        <Link to="/">Stocks</Link>
                    </li>
                </ul>
            </Navigation>
        </Navbar>
    )
}
