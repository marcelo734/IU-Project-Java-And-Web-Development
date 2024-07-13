import {ReactNode} from "react";
import styled from "styled-components";

type CardProps = {
    children: ReactNode
}

const CardWrapper = styled.div`
    background-color: #FFF;
    margin-bottom: 16px;
    padding: 16px;
`

export function Card({children}: CardProps) {
    return <CardWrapper>
        {children}
    </CardWrapper>
}
