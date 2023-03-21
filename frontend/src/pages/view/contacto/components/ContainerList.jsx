import { ContainerListStyled } from "../styled-components";

const ContainerList = ({ items }) => {
	return (
		<ContainerListStyled>
			{items.map((item, index) => (
				// rome-ignore lint/suspicious/noArrayIndexKey: <explanation>
				<li key={index}>{item}</li>
			))}
		</ContainerListStyled>
	);
};

export default ContainerList;
