import React from "react";
import { ContainerStyled } from "../styled-components/layout.styled";
const Container = ({ children, flexDirection, alignItems, padding }) => {
	return (
		<ContainerStyled
			flexDirection={flexDirection}
			alignItems={alignItems}
			padding={padding}
		>
			{children}
		</ContainerStyled>
	);
};

export default Container;
