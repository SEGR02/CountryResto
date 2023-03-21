import { useState } from "react";
import {
	PasswordContainer,
	PasswordWrapper,
	ShowPasswordIcon,
} from "../styled-components/InputPassword.styled";
import Icon from "./Icon";

function InputPassword({
	error = false,
	type,
	setError,
	name,
	data,
	setData,
	passwordToValidate = "",
}) {
	const [showPassword, setShowPassword] = useState(false);
	const errorMessage =
		"La contraseña debe tener entre 6 y 16 caracteres entre numero y letras";
	const errorMessageConfirm = "Las contraseñas no coinciden";

	const validate = (value) => {
		if (type === "password") {
			/^[a-zA-Z0-9]{6,16}$/.test(value) ? setError(false) : setError(true);
		}
		if (type === "confirmPassword") {
			value === passwordToValidate ? setError(false) : setError(true);
		}
	};

	return (
		<PasswordContainer>
			<label htmlFor="password">{name}</label>
			<PasswordWrapper error={error}>
				<input
					type={showPassword ? "text" : "password"}
					name='password'
					value={data}
					onChange={(e) => setData(e.target.value)}
					onBlur={(e) => validate(e.target.value)}
				/>
				<ShowPasswordIcon
					type='button'
					onClick={() => setShowPassword(!showPassword)}
				>
					<Icon />
				</ShowPasswordIcon>
			</PasswordWrapper>
			{error && (
				<span>{type === "password" ? errorMessage : errorMessageConfirm}</span>
			)}
		</PasswordContainer>
	);
}

export default InputPassword;
