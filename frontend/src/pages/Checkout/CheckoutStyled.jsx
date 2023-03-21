import styled from "styled-components";

export const CheckContainer = styled.section`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  justify-content: space-between;
  min-block-size: 100vh;
  background-color: var(--background-secondary);
  color: var(--text-primary);
  font-family: var(--font-family);
  /* @media (max-width: 1329px){
    max-height: 100%;
}
@media (max-width: 768px){

} */
`;
export const CheckData = styled.form`
  flex: 1;
  max-block-size: 547px;
  padding: 0px 93px;

  .subtitulos {
    font-family: var(--font-family);
    font-size: 24px;
    margin: 0px 0 5px 0;
    font-weight: 500;
  }

  .menor {
    font-family: var(--font-family);
    font-size: 20px;
    font-weight: 500;
  }

  .envio {
    padding-bottom: 39px;
  }
  hr {
    background-color: white;
    margin: 0px 0 38px 0;
  }
  input[type="radio"] {
    accent-color: var(--button-primary);
    position: relative;
    margin-right: 10px;
    cursor: pointer;
    width: 20px;
    height: 20px;
  }
  button {
    /*     background-color: var(--button-primary);
    color: black;
    font-weight: bold;
    width: 100%;
    height: 50px;
    font-size: 1.6rem; */
  }
  div {
    /* display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
    min-inline-size: 239px; */
    /*     label{
        font-size: 1.2rem;
        margin: 15px;
    } */
    input {
      /* height: 30px;
        background-color: var(--background-secondary);
        color: white; */
    }
  }
  @media (max-width: 1320px) {
    height: 100vh;
    width: 50%;
  }
  @media (max-width: 576px) {
    height: 100%;
    width: 100%;
  }
`;
export const SubtitleStyled = styled.div`
  position: relative;
  & ::after {
    content: "";
    position: absolute;
    inset-block-end: ${({ bottom }) => bottom};
    inset-inline: 0;
    block-size: 1px;
    inline-size: 476px;
    background-color: rgba(172, 172, 172, 1);
  }
`;
export const ContainerStyled = styled.div`
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;
  gap: 20px;
  min-inline-size: 239px;
`;

export const CheckedStyled = styled.div`
  display: flex;
  align-items: center;
`;

export const CheckCart = styled.section`
  flex: 1;
  background-color: white;
  color: black;
  display: flex;
  flex-direction: column;
  //height: 100vh;
  min-block-size: 100vh;
  padding: 28px 30px;

  .subtitulos {
    font-family: var(--font-family);
    font-size: 24px;
    margin: 0px 0 5px 0;
    font-weight: 500;
  }
  .detallePlato {
    display: flex;
    flex-flow: row;
    justify-content: space-between;
    margin-bottom: 10px;
  }
  .detallesub {
    display: flex;
    flex-flow: row;
    justify-content: space-between;
    align-items: center;
  }
  hr {
    background-color: rgba(172, 172, 172, 1);
    margin: 0px 0 38px 0;
  }
  @media (max-width: 1320px) {
    height: 100vh;
    width: 50%;
  }
  @media (max-width: 576px) {
    height: 100vh;
    width: 100%;
    border: none;
  }
`;
export const Unwrapped = styled.div`
  select {
    position: relative;
    -webkit-appearance: button;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    display: inline-block;
    vertical-align: middle;
    height: 2.2em;
    margin: 2px;
    padding: 0.3em 0.6em;
    background: white;
    border: 1px solid #aaaaaa;
    border-radius: 0.3em;
    box-shadow: inset 0 1px 2px rgba(255, 255, 255, 0.3),
      0 1px 2px rgba(0, 0, 0, 0.4);
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 0.85em;
    font-weight: normal;
    overflow: hidden;
    text-shadow: 0 1px 0 white;
    -webkit-transition: box-shadow 200ms, border-color 200ms;
    -moz-transition: box-shadow 200ms, border-color 200ms;
    transition: box-shadow 200ms, border-color 200ms;
    white-space: nowrap;
    select option {
      text-shadow: none;
    }
  }
`;

/* --------THANKS---------- */
export const ThxContainer = styled.div`
  height: 100vh;
  margin: 0px;
  background-color: var(--background-primary);
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  img {
    height: 50px;
    width: 50px;
  }
  button {
    background-color: var(--button-primary);
    color: black;
    height: 50px;
    width: 533px;
    left: 0px;
    top: 0px;
    border-radius: 4px;
  }
  button:hover {
    background-color: #ffc451;
    color: black;
    cursor: pointer;
  }
`;

export const FieldsetStyled = styled.fieldset`
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  min-inline-size: 239px;
  border: none;
  margin: 0;
  padding: 0;
  margin-bottom: 12px;
  legend {
    margin-bottom: 12px;
  }
`;

export const ContainerInputsStyled = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-start;
  flex-wrap: wrap;
  margin-top: 26px;
  gap: 16px;
`;

export const InputStyled = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  gap: 10px;
  block-size: 89px;
  input {
    position: relative;
    inline-size: ${({ inlineSize }) => inlineSize};
    padding: 0.9em;
    border: 1.5px solid #acacac;
    border-radius: 4px;
    font-weight: 500;
    font-size: var(--letterscustomizable-7);
    color: var(--text-secondary);
    background-color: var(--text-primary);
    &::placeholder {
      font-weight: bold;
      color: var(--text-secondary);
    }
    border: none;
    outline: ${({ outline }) =>
      outline ? "1px solid rgba(204, 16, 16, 1);" : "none"};
  }

  label {
    margin-right: 0;
    font-weight: 500;
    font-size: var(--letterscustomizable-6);
    text-align: left;
  }

  span {
    position: absolute;
    display: inline-block;
    min-inline-size: 400px;
    inset-block-end: ${({ bottom }) => bottom};
    font-weight: 300;
    font-size: 16px;
    color: rgba(204, 16, 16, 1);
  }
`;

export const ContainerFoodStyled = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-between;
  strong {
    display: inline-flex;
    gap: 4px;
    font-weight: 500;
  }
  p {
    font-weight: 500;
  }

  &::after {
    content: "";
    position: absolute;
    inset-block-end: ${({ bottom }) => bottom};
    inset-inline: 0;
    block-size: 1px;
    inline-size: 100%;
    background-color: rgba(172, 172, 172, 1);
  }
`;

export const ContainerTotalStyled = styled.div`
  margin-top: 79px;

  & div {
    display: flex;
    justify-content: space-between;
  }

  b {
    font-weight: 500;
  }
  & div:nth-child(2) {
    margin-top: 40px;
  }

  & div:nth-child(3) {
    display: flex;
    flex-direction: column;
    span {
      order: 2;
      font-weight: 400;
      color: var(--text-secondary);
    }
    margin-top: 40px;
    margin-bottom: 23px;
    div {
      order: 2;
      b:nth-child(2) {
        font-size: var(--letterscustomizable-4);
      }
    }
  }
`;
export const Button = styled.button`
  block-size: 56px;
  inline-size: 476px;
  border-radius: 4px;
  margin-block-start: 19px;
  font-weight: 600;
  font-size: var(--letterscustomizable-6);
  background-color: var(--button-primary);
  transition: background-color 1s ease-in;
  &:hover {
    background-color: var(--button-secondary);
  }
`;
