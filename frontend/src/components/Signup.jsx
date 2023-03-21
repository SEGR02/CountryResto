import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import Input from "./Input";
import InputPassword from "./InputPassword";
import { setCredentials } from "../redux/slice/Auth.slice";
import { useSignupMutation } from "../redux/query/auth.query";
import {
  AuthContainer,
  AuthLayout,
  Button,
  Form,
  FormContainer,
  LogoContainer,
  Title,
} from "../styled-components/Auth.styled";
import Logo from "/assets/logo.svg";

function Signup({ setShowAlert }) {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");

  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState(false);

  const [confirmPassword, setConfirmPassword] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState(false);

  const [showPassword, setShowPassword] = useState(false);
  const [showPassword2, setShowPassword2] = useState(false);

  const [signup, { isSuccess, error }] = useSignupMutation();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (passwordError || confirmPasswordError) {
      setConfirmPasswordError(true);
      return;
    }
    try {
      const response = await signup({
        name,
        email,
        password,
        role: "NORMAL",
      }).unwrap();
      dispatch(setCredentials({ ...response }));
      window.localStorage.setItem("id", response.id);
      window.localStorage.setItem("accessToken", response.accessToken);
      window.localStorage.setItem("refreshToken", response.refreshToken);
      window.localStorage.setItem("role", response.role);
      setName("");
      setEmail("");
      setPassword("");
      navigate("/myaccount");
    } catch (error) {
      console.log(error);
      setShowAlert(true);
      setTimeout(() => setShowAlert(false), 3000);
    }
  };

  return (
    <AuthContainer>
      <FormContainer>
        <Title>Registro</Title>

        <Form onSubmit={(e) => handleSubmit(e)}>
          <Input
            name="Nombre"
            type="text"
            data={name}
            setData={setName}
            pattern={/^[A-Za-z]+(?:\s[A-Za-z]+)*$/}
          />

          <Input
            name="Correo"
            type="email"
            data={email}
            setData={setEmail}
            pattern={/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/}
          />

          <InputPassword
            name="Contraseña"
            error={passwordError}
            setError={setPasswordError}
            data={password}
            setData={setPassword}
            type="password"
          />
          <InputPassword
            name="Confirmar Contraseña"
            error={confirmPasswordError}
            setError={setConfirmPasswordError}
            data={confirmPassword}
            setData={setConfirmPassword}
            type="confirmPassword"
            passwordToValidate={password}
          />

          <Button> Registrarse </Button>
        </Form>
      </FormContainer>
    </AuthContainer>
  );
}

export default Signup;
