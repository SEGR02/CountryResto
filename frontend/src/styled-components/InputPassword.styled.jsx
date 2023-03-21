
import styled from "styled-components"

export const PasswordContainer = styled.div`
font-size: 16px;
margin-bottom: 20px;
& > span {
  color: red;
  font-size: 12px;
}
`;

export const PasswordWrapper = styled.div`
  border: 1px solid #ccc;
  height: 59px;
  display: flex;
  position: relative;
  border-radius: 6px;
  padding: 5px;
  margin: 10px;
  margin-left: 0;
  margin-bottom: 0;
  width: 100%;
  &>input{
    border: none;
    font-size: 15px;
    padding-left: 30px;
    width: 100%;
    margin: 0;
    &:focus {
      outline: none;
    }
  }
  ${props => {
    if (props.error) {
      return `
        border: 1px solid red;
      `
    }}
    }
&>span {
  font-size: 10px;
}
 `;

export const ShowPasswordIcon = styled.button`
  &:focus {
    outline: none;
    background: none;
  }
  background: none;
  border: none;
`;
