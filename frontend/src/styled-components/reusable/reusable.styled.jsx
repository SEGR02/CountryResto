import { css } from "styled-components";

// TODO CENTRADO GENERAL //

export const FlexBoxCenter = css`
    display: flex;
    justify-content: center;
    align-items: center;
`;

// TODO Background para los etiquetas button //

export const BackgroundImageButtons = css`
    background-image: url(${({ img }) => img});
    background-repeat: no-repeat;
    background-size: ${({ size }) => size};
`;
