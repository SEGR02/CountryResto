import { useState } from "react";
import { InputContainer } from "../styled-components/Input.styled.";

function Input({ name, type, data, setData, pattern }) {
  const [error, setError] = useState(false)
  const validation = () => {
    if (pattern) {
      if (pattern.test(data)) {
        setError(false)
      } else {
        setError(true)
      }
    }
  }

	return (
		<InputContainer error={error}>
			<label htmlFor={name}>{name}</label>
			<input
				type={type}
				name={name}
        value={data}
        required={true}
        onChange={(e) => setData(e.target.value)}
        onBlur={validation}
			/>
			{error && <span>{name} no valido</span>}
		</InputContainer>
	);
}

export default Input;
  