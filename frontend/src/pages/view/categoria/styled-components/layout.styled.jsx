import styled, { keyframes } from "styled-components";
import {
	BackgroundImageButtons,
	FlexBoxCenter,
} from "../../../../styled-components";

export const CategoryStyled = styled.section`
    display: grid;
    grid-template-columns: repeat(12, 1fr);
    grid-template-rows: 1fr;
    grid-column-gap: 30px;
    grid-row-gap: px;
    font-family: var(--font-family);
    article{
        grid-area: 1 / 2 / 2 / 12;
    }
`;

export const CardStyled = styled.article`
    position: relative;
    height: 366px;
    width: 300px;
    left: 0px;
    top: 0px;
    border-radius: 20px;
    background: #FFFFFF;
    a{
        position: absolute;
        inset-block-end: 0;
        inset-inline: 0;
        block-size: 100%;
        border-radius: 20px ;
        text-align: center;
        color:var(--background-primary);
        ${BackgroundImageButtons};
        h2{
            position: absolute;
            inset-block-end: 2%;
            inset-inline: 12%;
        }        
    }
`;

export const ItemFoodStyled = styled.article`
    position: relative;
    ${FlexBoxCenter};
    flex-direction: column;
    min-block-size: 457px;
    inline-size: 365px;
    padding: 20px 0;
    border-radius: 20px;
    text-align: center;
    color: var(--background-primary);
    background-color: var(--text-primary);

    header{
        position: relative;
        min-block-size: 137px;
        inline-size: 100%;
        img{
            position: absolute;
            inset-block-start: -80%;
            inset-inline: 16%;
        }
    }

    h2{
        font-weight: 500;
    }
    
    b{
        display: inline-block;
        min-inline-size: 365px;
        padding: 0 23px;
    }
    
    p{  

        font-size: var(--letterscustomizable-7);
        text-align: center;
        & + strong {
            margin-bottom: 12px;
            font-size: 24px;
        }
    }
    

    a{  
        display: inline-flex;
        align-items: center;
        justify-content: center;
        gap: 12px;
        padding: .8em 2em;
        border-radius: 4px;
        background-color: var(--button-primary);
        transition: background-color 1s ease-out;
        & strong{
            font-weight: 500;
        }
        &:hover{
            background-color: var(--button-secondary);
        }
    }

`;
export const DivGrid = styled.div`
display: flex;
flex-wrap: wrap;
justify-content: center;
align-items: center;
padding: 25px;
article{
margin: 30px;
}


`;
export const StarStyled = styled.div`
    position: absolute;
    inset-block-start: 22%;
    inset-inline: 38%;
    ${FlexBoxCenter};
    gap: 12px;
    block-size: 47px;
    inline-size: 84px;
    padding: 12px 16px;
    color: var(--text-primary);
    border-radius: 50px;
    background-image: linear-gradient(180deg, #323232 0%, #0B0B0B 100%);
`;

/*===================== CATEGORY LOADER =====================*/

const Loader = keyframes`

    50%{
        background-color: rgb(212, 212, 212);   
    }
`;

export const CategoryLoaderStyled = styled.div`
    
    position: relative;
    block-size: 366px;
    inline-size: 300px;
    border-radius: 20px;
    background-color: var(--text-primary);
    animation: ${Loader} 1s infinite linear;
    
    span{
        position: absolute;
        inset-block-end: 9%;
        inset-inline: 0%;
        block-size: 38px;
        inline-size: 90%;
        margin: 0 auto;
        border-radius: 8px;
        background-color: #d7d7d7b0;
    }

`;

/*===========================================================*/
