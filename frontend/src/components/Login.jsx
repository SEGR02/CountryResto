import { useState } from "react";
import { useDispatch } from "react-redux";
import { useLocation, useNavigate, Link } from "react-router-dom";
import Input from "./Input";
import InputPassword from "../components/InputPassword";
import { useLoginMutation } from "../redux/query/auth.query.js";
import { setCredentials } from "../redux/slice/Auth.slice.js";
import {
  AuthContainer,
  AuthLayout,
  Button,
  Form,
  FormContainer,
  LinkRegister,
  LogoContainer,
  Title,
  ForgotPassword,
} from "../styled-components/Auth.styled";

function Login({ navigateTo = "/", setShowAlert }) {
  const [email, setEmail] = useState("");

  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState(false);

  const { pathname } = useLocation();
  const navigate = useNavigate();

  const [login, { isLoading }] = useLoginMutation();
  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await login({ email, password }).unwrap();
      dispatch(setCredentials({ ...response }));
      window.localStorage.setItem("id", response.id);
      window.localStorage.setItem("accessToken", response.accessToken);
      window.localStorage.setItem("refreshToken", response.refreshToken);
      window.localStorage.setItem("role", response.role);
      setEmail("");
      setPassword("");
      navigate("/");
    } catch (error) {
      console.log(error);
      setShowAlert(true);
      setTimeout(() => setShowAlert(false), 3000);
    }
  };

  return (
    <AuthContainer>
      <FormContainer>
        <Title>Iniciar sesión</Title>

        <Form onSubmit={(e) => handleSubmit(e)}>
          <Input
            name="Correo"
            data={email}
            setData={setEmail}
            type="email"
            pattern={/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/}
          />
          <InputPassword
            error={passwordError}
            name="Contraseña"
            data={password}
            setData={setPassword}
          />

          <ForgotPassword>
            <Link to="/users/123">¿Olvidaste tu contraseña?</Link>
          </ForgotPassword>
          <Button type="submit">Iniciar sesión</Button>
        </Form>

        <LinkRegister>
          ¿No tienes cuenta? <Link to="/signup">Regístrate</Link>
        </LinkRegister>
      </FormContainer>
    </AuthContainer>
  );
}

export default Login;
