import styled, { keyframes } from "styled-components";
import { FlexBoxCenter } from "../../../../styled-components";

export const ViewOfThePlateStyled = styled.section`
    ${FlexBoxCenter};
    flex-direction: column;
    min-block-size: 100vh;  
    inline-size: 100%;
    margin-top: 2em;
    text-align: center;
    color: var(--text-primary);
    background-color: var(--background-primary); 
    z-index: 2;
    overflow: hidden;
        @media  screen and (min-width: 900px){
      text-align: left;
    }
    hr{
        inline-size: 88%;
        margin-top: 60px;
    }
`;

export const ContainerStyled = styled.div`
    display: flex;
    flex-wrap: wrap;
    
    align-items: ${({ alignItems }) => alignItems || "center"};
    justify-content: space-evenly;
    flex-direction: ${({ flexDirection }) => flexDirection || "row"};
    inline-size: 100%;
    min-block-size: 570px;
    padding: ${({ padding }) => padding || "0px"};
    @media (max-width: 867px) {
      >img{
        min-width: 200px;
        max-width: 400px;
        height: auto;
        padding: 0 2em;
      }
    }

`;

export const ArticleStyled = styled.article`

    display: flex;
    flex-direction: column;
   min-block-size: 307px;
    inline-size: 468px;

`;

export const HeaderArticleStyled = styled.header`
      
    display: flex;
    align-items: center;
    justify-content: space-between;
    inline-size: 100%;
    font-size:  1.5rem;
      h2{
        font-weight: 500;
      }
       div{
        display: inherit;
        align-items: center;
        gap: 12px;
        p{
          font-size: 1.2rem;
        }
      }
      @media (max-width: 866px) {
      flex-direction: column;
      h2 {
        font-size: 30px;
        margin: .5em 0;
      }
      p {
        margin: 0;
      }
    }
`;

export const DescriptionStyled = styled.div`
   strong{
      font-size: var(--letterscustomizable-7);
      font-weight: 300;
    }

    p:nth-child(2) {
      min-inline-size: 486px;      
      font-weight: 300;
      padding-right: 1em;
    }

    p:nth-child(4) {
      font-size: 2rem;
      font-weight: 500;
    } 
`;

export const FooterArticleStyled = styled.footer`

    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    align-items: center;
    inline-size: 500px;
    block-size: 400px;
    margin-block-start: .6em;

    span{
      min-inline-size: 19px;
      text-align: center;
    }
    @media (max-width: 551px) {
      flex-wrap: wrap;
      justify-content: center;
      gap: 2em;
      inline-size: auto;
      block-size: auto;
      > button {
        min-width: calc(100vw - 40vw);
        inline-size: auto;
      }
    }
`;

export const ContainerFooterStyled = styled.div`
  ${FlexBoxCenter};
  color: var(--background-nav-bar);
  background-color: var(--button-primary);
  border-radius: 4px;
  button{
    padding: 1em;
  }
  & + button{
    ${FlexBoxCenter};
    gap: 15px;
    block-size: 45px;
    inline-size: 253px;
    border-radius: 4px;
    background-color: var(--button-primary);
    span{
      font-size: var(--letterscustomizable-7);
      font-weight: 600;
    }
  }
`;

export const StarIconStyled = styled.svg`

    --size: 25px;
      block-size: var(--size);
      inline-size: var(--size);
`;

export const FormStyled = styled.form`
    display: flex;
    flex-direction: column;
    inline-size: 100%;
    label{
        font-size: 1.3rem;

    }
    input{
        margin-top: 18px;
        padding: 1em;
        border-radius: 5px;
        color: var(--text-primary);

        background-color: transparent ; 
    }
    
`;

export const ContainerStartStyled = styled.header`
    display: flex;
    align-items: center;
    gap: 80px;
    div{
        display: flex;
    }
`;

const Loader = keyframes`

    50%{
      background-color:#f0f0f0;      
    }
`;
export const ViewOfThePlateLoaderStyled = styled.div`    

    display: flex;
    align-items: center;
    justify-content: space-evenly;
    gap: 90px;
    flex-wrap: wrap;
    block-size: 800px;
    inline-size: 100%;   
`;

export const DivLoaderStyled = styled.div`
    --size: 400px;
    block-size: var(--size);
    inline-size: var(--size);
    border-radius: 50%;
    background-color:#c3c3c3;
    animation: ${Loader} 1s infinite  linear;
`;

export const DescriptionLoaderStyled = styled.div`
    --size: 400px;
    display: flex;
    align-items: flex-start;
    justify-content: center;
    flex-direction: column;
    gap: 24px;
    block-size: var(--size);
    inline-size: var(--size);
`;

export const SpanLoaderStyled = styled.span`
  display: inline-block;
  block-size: ${({ blockSize }) => blockSize};
  min-inline-size: ${({ inlineSize }) => inlineSize};
  background-color:#c3c3c3;
  border-radius: 6px;
  animation: ${Loader} 1s infinite  linear;
`;
