import styled from "styled-components";

export const AuthLayout = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: var(--background-primary);
  width: 100%;
  height: 100vh;
  color: var(--background-primary);
  font-family: var(--font-family);
`;

export const AuthContainer = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  border-radius: 10px;
  width: 505px;
  margin: 1em;
  padding: 1em;
  padding-top: 72px;
  font-size: 31px;
`;

export const LogoContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  @media (max-width: 1000px) {
    display: none;
  }
`;

export const Title = styled.h3`
  margin-bottom: 73px;
`;

export const FormContainer = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  font-size: 16px;
`;

export const Button = styled.button`
  margin-top: 56px;
  border-radius: 6px;
  height: 57px;
  background-color: #ffa800;
  font-size: 20px;
  font-weight: 600;
  :hover {
    background-color: #ffc451;
    color: black;
    cursor: pointer;
  }
`;

export const LinkRegister = styled.div`
  font-size: 16px;
  margin-top: 56px;
  text-align: center;
  & > a {
    font-weight: 600;
    color: black;
    text-decoration: none;
  }
`;

export const ForgotPassword = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  & > a {
    text-decoration: none;
    color: #4d4d4d;
  }
`;
