import styled from 'styled-components'

export const InputContainer = styled.div`
  margin-bottom: 20px;
  font-size: 16px;
& > input{
  border-radius: 10px;
  border: 1px solid #ccc;
  width: 100%;
  height: 59px;
  padding-left: 30px;
  margin: 9px;
  margin-bottom: 0;
  margin-left: 0;
  /* &:ms-reveal {
    border: 1px solid transparent;
    border-radius: 50%;
    box-shadow: 0 0 3px currentColor;
  } */
  ${props => {
    if (props.error) {
      return `
        border: 1px solid red;
      `;
    }}}
}
& > span {
  font-size: 12px;
  color: red;
}
`;