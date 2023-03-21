import styled, { keyframes } from "styled-components";
import {
	BackgroundImageButtons,
	FlexBoxCenter,
} from "../reusable/reusable.styled";

export const HeaderStyled = styled.header`
    position:sticky;
    inset-block-start:0;
    inset-inline:0 ;
    display: none;
    display: flex;
    /* justify-content: space-around; */
    align-items: center;
    /* gap: 20%; */
    block-size: 106px;
    inline-size: 100%;
    padding: 0 3em;
    background-color: var(--background-primary);
    z-index: 3;
    a{
      display: inline-block;
      /* flex-shrink: 0; */
      margin-right: auto;
    }
    @media (max-width: 801px) {
      justify-content: space-between;
      > a{
        margin-right: unset;
        pointer-events: none;
        cursor: default;
      }
    }
    @media (max-width: 801px) {
      height: 70px;
      img {
        scale: .7
      }
    }

`;

export const HeaderSubtitleStyled = styled.header`
    padding: 1em;
    font-size:${({ fontSize }) => fontSize || "1rem"};
    font-weight:${({ fontWeight }) => fontWeight || "bold"};
    color: ${({ color }) => color || "#fff"};
    text-align: ${({ textAlign }) => textAlign}; 

`;

//TODO Div responsive abierto a modificar */
export const Div = styled.div`
    ${FlexBoxCenter};
    flex-wrap: wrap;
    gap: ${({ gap }) => gap || "0px"};
    min-block-size: ${({ blockSize }) => blockSize};
    inline-size: ${({ inlineSize }) => inlineSize};
    margin: ${({ margin }) => margin || "0px"};
    padding:${({ padding }) => padding || "0px"};
`;
//TODO Div responsive abierto a modificar */
export const DivCol = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    gap: 17px;
    margin:15px;
    justify-content: space-around;
    flex-wrap: wrap;
    
    &::after{
      content: '';
        position: absolute;
        inset-block-end: 0;
        inset-inline: 0;
        block-size: 1px;
        inline-size: 100%;
        background-color:  rgba(172, 172, 172, 1);
    }
`;

export const ModalStyled = styled.section`

    ${FlexBoxCenter};
    flex-direction: column;
    min-block-size: 100vh;  
    inline-size: 100%;
    color: var(--text-primary);
    background-color: var(--background-primary); 
    z-index: 2;
    hr{
      inline-size: 97%;
    }
`;

export const HeroImageStyled = styled.img`
  
    block-size: ${({ blockSize }) => blockSize};
    inline-size: ${({ inlineSize }) => inlineSize};
    text-align: center;
    

`;

export const ButtonModalStyled = styled.button`

    position: absolute;
    inset-block-start: 17%;
    inset-inline-end: 9%;
    padding: ${({ padding }) => padding} ; 
    ${BackgroundImageButtons};
    
`;
export const Wrapperremember = styled.div`
width:100%;
display: flex;
justify-content:space-between;
align-items:center;
`;

export const Wrappersignup = styled.div`
width:100%;
display: flex;
padding-left: 3em;
align-items:center;

`;

export const FormWrapper = styled.div`
  display: flex;
  flex-direction: column;
  border: 1px solid #ccc;
  border-radius: 10px;
  width: 30%;
  min-width: 300px;
  margin: auto;
  padding: 1em;
  
`;

export const Label = styled.label`
  /* margin: 10px; */
`;

export const Input = styled.input`
  border-radius: 10px;
  padding: 10px;
  margin: 10px;
  margin-bottom: 20px;
  margin-left: 0;
  &:ms-reveal {
    border: 1px solid transparent;
    border-radius: 50%;
    box-shadow: 0 0 3px currentColor;
  }
`;

export const PasswordInput = styled.input`
  border: none;
  width: 100%;
  &:focus {
    outline: none;
  }
`;

export const PasswordContainer = styled.div`
  border: 1px solid #000;
  display: flex;
  position: relative;
  border-radius: 10px;
  padding: 5px;
  margin: 10px;
  margin-bottom: 20px;
  margin-left: 0;
 `;

/* export const Button = styled.button`
  block-size: 56px;
  inline-size: 476px;
  border-radius: 4px;
  margin-block-start: 19px;
  font-size: var(--letterscustomizable-6);
  background-color: #49454F;
  
 `;
 */
export const ShowPasswordIcon = styled.button`
  &:focus {
    outline: none;
    background: none;
  }
  background: none;
  border: none;
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
`;

// TODO REUTILIZABEL FORM REGISTER ANDA LOGIN //
export const SignupBox = styled.div`
  display: flex;
  flex-direction: column;
  margin: auto;
  width: 100%;
  height: 100%;
`;

export const Title = styled.h3`
  
`;

export const CarListStyled = styled.aside`
  position : fixed;
  inset-inline-end: 0;
  inset-block: 0;
  inline-size: 423px;
  padding-top: 3em;
  padding-left:1em;
  padding-right:1em;
  background-color: var(--background-secondary);
  transform: ${({ translateX }) => translateX};
  transition: transform 1s ease-in-out; 
  z-index:3;

  header{
    display: flex;
    align-items: center;
    inline-size: 70%;
    gap: 12px;
    color: var(--button-primary);

  }

  section{
    block-size: 245px;
    overflow-y: scroll;
    ${({ length }) => {
			if (length === 0) {
				return `
        ${FlexBoxCenter};
            color: var(--text-primary);
        `;
			}
		}}
      
  }
  section + hr{
    inline-size: 100%;
  }
  footer{
    margin-top: 17px;
  }

  footer div{
    display: flex;
    align-items: center;
    justify-content: space-between;
    inline-size: 100%;
    margin-top: 17px;
    color: var(--text-primary);
    b{
      font-weight: 400;
    }
  }
  
  footer div:nth-child(2){
      display: flex;
      align-items: center;
      flex-direction: column;
      gap: 20px;
      a{
        color: var(--background-primary);
      }
      button{
        color: var(--background-primary);
        font-weight: 600;
        padding: .7em 2.7em;
        border-radius: 4px;
        background-color: var(--button-primary);
        transition: background-color 1s ease-in-out; 
        &:hover{
          background-color: var(--button-secondary);
        }
      }
      a:nth-child(2){
        color: var(--text-primary);
        text-decoration:  underline ;
      }
      button.disable {
        background-color: var(--button-disabled);
        pointer-events: none;
        cursor: default;
      }
   }

`;

export const ItemCartStyled = styled.article`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 19px;
    color: var(--text-primary);
    p{
      display: flex;
      gap: 10px;
    }
    div{
      display: inherit;
      align-items: inherit;
      gap: 12px;
    }

  `;

export const ButtonDeleteItemCartStyled = styled.button`
    padding: 1em;
    ${BackgroundImageButtons};
    background-position: center; 

`;

export const ButtonCarListExitStyled = styled.button`
  position: absolute;
  inset-block-start: 4%;
  inset-inline-end: 5%;
 ${BackgroundImageButtons};
  padding: .6em;
`;

export const ContainerModelStyled = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    inline-size: 100%;
    min-block-size: 570px;

`;

export const ArticleModal = styled.article` 
    display: flex;
    flex-direction: column;
    block-size: 307px;
    inline-size: 468px;
    
    header{
      display: inherit;
      align-items: center;
      justify-content: space-between;
      inline-size: 100%;
      h2{
        font-size: 2rem;
        font-weight: 500;
      }
      h2 + div{
        display: inherit;
        align-items: center;
        gap: 12px;
      }
    }


    header +  div strong{
      font-size: 1.5rem;
      font-weight: 500;
    }

    header + div:nth-child(2) {
      min-inline-size: 636px;      
      font-weight: 300;
    }

    header + div p:last-child{
      font-size: 2rem;
      font-weight: 500;
    } 

    footer{
      display: flex;
      justify-content: space-evenly;
      align-items: center;
      margin-block-start: .6em;
     button{
       background-color: var(--button-primary);
      }

      span{
        min-inline-size: 19px;
        text-align: center;
      }

      div button{
        padding: 1em ;  
      }


      button:nth-child(3){
        display: flex;
        align-items: center;
        gap: 17px;
        padding: 1em 5em;
        border-radius: 4px;
        span{ 
          font-weight: bold;
          font-size: 1rem;
        }
      }

    }

`;

export const BadgeStar = styled.div`
  transform: translateY(-1em);
  width: 84.7px;
  height: 47.72px;
  color: #FFF;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(180deg, #323232 0%, #0B0B0B 100%);
  border-radius: 50px;
`;

export const ButtonActionStyled = styled.button`
    position: relative;
    padding: ${({ padding }) => padding};
    color: ${({ color }) => color || "none"};
    background-color: ${({ backgroundColor }) => backgroundColor};
    ${BackgroundImageButtons};
    transition: background-color 1s ${({ transition }) => transition};

    &::before{
      --size: 20px;
      position:absolute;
      inset-block-start: -17%;
      inset-inline-end: -47%;
      content: attr(data-length);
      block-size: var(--size);
      inline-size: var(--size);
      line-height: 20px;
      color: var(--text-primary);
      border-radius: 50%;
      background-color: rgba(204, 16, 16, 1);
      opacity: ${({ opacity }) => (opacity ? "hidden" : "visible")};
      transition: opacity 1s ease-in-out;
    }
    &:hover{
      background-color: ${({ backgroundColorHover }) =>
				backgroundColorHover || "none"};
    }
`;

export const NavBarStyled = styled.nav`
    min-inline-size: 464px;
    display: flex;
    justify-content: space-around;
    /* margin: 1em; */
    menu{
      display: flex;
      gap: 20px;
      inline-size: 100%;
      padding: 0;
      li {
        min-width: fit-content;
      }
    }

    menu li a{
      color: var(--text-primary);
      transition: color 1s ease-in-out;
      &:hover{
        color: var(--button-primary);
      }

      &.isActive{
        color: var(--button-primary);
      }
    }
    @media (max-width: 801px) {
      display: none;
    }
`;

export const NavBottomStyled = styled.div`
    display: none;
    font-size: 12px;
    menu {
      display: flex;
      height: 70px;
      margin: 0;
      padding: 0;
      justify-content: space-around;
      p{
        margin: 0;
      }
    }
    @media (max-width: 801px) {
      display: block;
      position: fixed;
      bottom: 0;
      left: 0;
      width: 100%;
      /* height: auto; */
      background-color: #000;
      /* overflow: hidden; */
      border-radius: 10px 10px 0px 0px;
      li{
        display: block;
        flex: 1 1 0px;
        a, span{
            display: flex;
            align-items: center;
            flex-direction: column-reverse;
            height: 85%;
            inline-size: 100%;
            img{
              height: 30px;
            }
            &.isActive{
              color: var(--button-primary);
              filter: brightness(0) saturate(100%) invert(64%) sepia(97%) saturate(1918%) hue-rotate(360deg) brightness(103%) contrast(106%);
            }
          }
      }
    }
`;
export const TabbarStyled = styled.div`
    display: none;

    img {
      display: block;
      scale: 1.2;
    }
    @media (max-width: 801px) {
      display: initial;
    }
`;
export const NavSideStyled = styled.nav`
  position: fixed;
  display: none;
  z-index: 100;
  height: 100%;
  width: 210px;
  left: 0px;
  top: 0px;
  transform: ${({ translateX }) => translateX};
  transition: transform 1s ease-in-out; 
  color: var(--text-primary);
  font-weight: 400;
  font-size: 14px;
  background: #0E0E0E;
  border-radius: 0px 10px 10px 0px;
  header {
    display: flex;
    justify-content: space-between;
    padding: 10px 0;
    margin: 0px 20px 15px;
    border-bottom: 1px solid #ffffff88;
    img:first-child{
      width: 64px;
    }
  }
  menu{
    display: flex;
    flex-direction: column;
    gap: 1em;
    padding: 20px;
  }
  @media (max-width: 801px) {
    display: block;
  }
`;

/* Container del item responsive abierto a que lo modifiquen */
export const ItemContainerStyled = styled.article`
  position: relative;
  min-block-size: 426px;
  min-inline-size: 365px;
  border-radius: 20px;
  background-color: #FFF;
  color: var(--background-primary);
  text-align: center;
  
  h2{
      margin-top: 37%;
      margin-bottom: 0;
      font-size: 28px;
      font-weight: 500;
  } 

  footer{
    block-size: 90px;
  }
  @media (max-width: 601px) {
    width: 200px;
    height: 250px;
    
  }
`;
export const ItemImgStyled = styled.img`
    position: absolute;
    inset-block-start: ${({ top }) => top || " -15%"}; 
    inset-inline: ${({ insetInline }) => insetInline || "9.1%"};
    block-size: ${({ blockSize }) => blockSize || "198px"};
    inline-size:${({ inlineSize }) => inlineSize || "300px"};
    border-radius:${({ borderRadius }) => borderRadius};
    text-align:center; 
`;

export const ItemDescription = styled.p`
    display: inline-flex;
    align-items: center;
    justify-content: center;
    min-block-size: 72px;
    max-inline-size: 300px;
    margin: 0;
    font-size: var(--letterscustomizable-7);
    font-weight: 500;
`;

/*================== BORRAR ==================*/
/* export const ItemButtonStyled = styled.button`
  
    width: 289px;
    height: 55px;
    font-size: 20px;
    margin: auto;
    border-radius: 4px;
    background-color: var(--button-primary);
    transition: background-color 1s ease-in-out;
    img {
        margin-left: .5em;
    }
    &:hover{
      background-color : var(--button-secondary);
    }

    span {
        font-weight: 600;
    }
`; */

export const ItemFooterStyled = styled.footer`
   
   & a{
     ${FlexBoxCenter};
     width: 289px;
     height: 55px;
     font-size: 20px;
     margin: auto;
     border-radius: 4px;
     background-color: var(--button-primary);
     transition: background-color 1s ease-in-out;
     img {
         margin-left: .5em;
     }
     &:hover{
       background-color : var(--button-secondary);
     }
 
     span {
         font-weight: 600;
     }
   }
`;
export const ItemPeople = styled.p`
    font-size: 16px;
    margin: .5em;
`;
export const ItemPrice = styled.p`
    font-size: 24px;
    font-weight: 600;
    margin: .5em;
`;

export const FooterStyled = styled.footer`
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  height: 205px;
  margin-top: 4rem;
  span {
    font-size: 20px;
    margin: 5px 0;
  }
  @media (max-width: 851px) {
    padding: 7em 2em;
    margin: auto;
    flex-direction: column;
    gap: 3rem;
    height: auto;
  }
`;

export const FooterContainerStyled = styled.div`
      display: inherit;
      justify-content: space-between;
      gap: 5em;
      div ul li{
        display: flex;
        align-items: center;
        gap:12px;
        font-size: 12px;
        img {
          height: 18px;
        }
        p{
          margin: 5px 0;
        }
      }
      @media (max-width: 601px) {
        flex-direction: column;
        text-align: center;
        gap: 3em;
        li {
          justify-content: center;
        }
      }
`;

export const FooterContainerTwo = styled.div`

    display: flex;
    flex-direction: column;
    ul{
      display: flex;
      justify-content: space-around;
    }
    img {
      height: 18px;
    }
`;

export const SectionStyledBackground = styled.section`
    ${BackgroundImageButtons};   
    background-attachment: fixed;
`;

/*================= ITEM LOADER ========================*/

const animationLoader = keyframes`

    50%{
      background-color: var(--text-primary);
    }  
`;

export const ItemLoaderStyled = styled.article`
    position: relative;
    block-size: 426px;
    inline-size: 365px;
    border-radius: 20px;
    text-align: center;
    background-color: var(--text-primary);

    span{
      display: inline-block;
      background-color: rgb(227, 227, 227);
      animation: ${animationLoader} 1s  linear infinite  ;
    }
    div{
      background-color: rgb(227, 227, 227);
      animation: ${animationLoader} 1s   linear infinite ;
    }

`;

export const HeaderLoaderStyled = styled.header`
    position: relative;
    block-size: 156px;
    inline-size: 400px;
`;

export const ImageLoaderStyled = styled.span`
    position: absolute;
    inset-block-start: ${({ top }) => top || "0%"};
    inset-inline: ${({ insetInline }) => insetInline || "0%"};
    block-size: ${({ blockSize }) => blockSize || "0px"}  ;
    inline-size:${({ inlineSize }) => inlineSize || "0px"}  ;
    border-radius: ${({ borderRadius }) => borderRadius || "0px"};

`;
export const TitleLoaderStyled = styled.span`
    block-size: 48px;
    inline-size: 80%;
    border-radius: 10px;
`;

export const ParagraphLoaderStyled = styled.span`
    block-size: 59px;
    inline-size: 87%;
    margin-top: 3px;
    border-radius: 10px;
`;

export const ForPeopleStyled = styled.span`

    block-size: 26px;
    inline-size: 48%;
    border-radius: 10px;
    margin-top: 4px;
`;

export const PrecisLoaderStyled = styled.span`
    block-size: 40px;
    inline-size: 60%;
    border-radius: 10px;
`;

export const ButtonLoaderStyled = styled.span`
    block-size: 55px;
    inline-size: 289px;
    border-radius: 5px;
`;

/*======================================================*/

/*=======================LOADER===============================*/

const Spin78236 = keyframes`
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
`;

const Wobble1 = keyframes`
    0%,
    100% {
        transform: translateY(0%) scale(1);
        opacity: 1;
    }

    50% {
        transform: translateY(-66%) scale(0.65);
        opacity: 0.8;
    }
`;

const Wobble2 = keyframes`
      
      0%,
      100% {
            transform: translateY(0%) scale(1);
            opacity: 1;
      }
      50% {
            transform: translateY(66%) scale(0.65);
            opacity: 0.8;
      }

`;
export const ContainerLoaderStyled = styled.div`
    position: fixed;
    inset-block: 0%;
    block-size: 100vh;
    inline-size: 100%;
    ${FlexBoxCenter};
    background-color: var(--color-primary);
`;

export const LoaderStyled = styled.div`

  --uib-size: 35px;
  --uib-speed: 0.8s;
  --uib-color: var(--button-primary);

    height: var(--uib-size);
    width: var(--uib-size);
    transform: translate(-50%, -50%);
    animation: ${Spin78236} calc(var(--uib-speed) * 2.5) infinite linear;

    & div{
      position: absolute;
      height: 25px;
      width: 25px;  

      &::after{
        content: '';
        position: absolute;
        height: 0%;
        width: 100%;
        padding-bottom: 100%;
        background-color: var(--uib-color);
        border-radius: 50%;

      }

      &:nth-child(1){
        bottom: 5%;
        left: 0;
        transform: rotate(60deg);
        transform-origin: 50% 85%;   
        &::after {
          bottom: 0;
          left: 0;
          animation: ${Wobble1} var(--uib-speed) infinite ease-in-out;
          animation-delay: calc(var(--uib-speed) * -0.3);
        }
      }

      &:nth-child(2) {
        bottom: 5%;
        right: 0;
        transform: rotate(-60deg);
        transform-origin: 50% 85%;
        &::after {
          bottom: 0;
          left: 0;
          animation: ${Wobble1} var(--uib-speed) infinite calc(var(--uib-speed) * -0.15) ease-in-out;
        }

      }

      &:nth-child(3) {
        bottom: -5%;
        left: 0;
        transform: translateX(116.666%);
        &::after{
          top: 0;
          left: 0;
          animation: ${Wobble2} var(--uib-speed) infinite ease-in-out;
        }
      }
  }
`;

export const AuthModalStyled = styled.div`
    position: fixed;
    inset-block: 0%;  
    inset-inline: 0%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 100;
    color: black;
    ${FlexBoxCenter};
`;
/*======================================================*/