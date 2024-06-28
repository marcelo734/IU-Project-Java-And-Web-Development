import {Link, useNavigate} from "react-router-dom";
import styled from "styled-components";
import {useCallback} from "react";

const Navbar = styled.header`
    padding: 8px 16px;
    box-shadow: 0 1px 2px grey;
    display: flex;
    justify-content: space-between;
    align-items: center;
`

const LogoWrapper = styled.div`
    background-color: #04274c;
    width: 120px;
    padding: 8px;

    &:hover {
        cursor: pointer;
    }
`
const Logo = styled.span`
    font-family: "Playwrite ES Deco", cursive;
    font-optical-sizing: auto;
    font-weight: 400;
    font-style: normal;
    font-size: 22px;
    color: #FFF;
    display: flex;
    align-items: center;
    justify-content: center;
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
    const navigate = useNavigate()

    const onLogoClick = useCallback(() => {
        navigate("/")
    }, [])

    return (
        <Navbar>
            <LogoWrapper onClick={onLogoClick}>
                <Logo>Stocks</Logo>
            </LogoWrapper>

            <Navigation>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                </ul>
            </Navigation>
        </Navbar>
    )
}
