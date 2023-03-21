import { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useLocation, useNavigate } from "react-router-dom";
import Alert from "../../components/Alert";
import { setCredentials } from "../../redux/slice/Auth.slice";
import { AuthLayout, LogoContainer } from "../../styled-components/Auth.styled";
import Login from "../../components/Login";
import Signup from "../../components/Signup.jsx";
import Logo from "../../../public/assets/logo.svg";

function AuthForm() {
  const [showAlert, setShowAlert] = useState(false);
  const { pathname } = useLocation();

  return (
    <AuthLayout>
      {pathname === "/login" ? (
        <Login setShowAlert={setShowAlert} />
      ) : (
        <Signup setShowAlert={setShowAlert} />
      )}
      {showAlert && <Alert />}
      <LogoContainer>
        <img src={Logo} alt="Logo" />
      </LogoContainer>
    </AuthLayout>
  );
}

export default AuthForm;
