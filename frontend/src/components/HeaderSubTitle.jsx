import { HeaderSubtitleStyled } from "../styled-components";
// TODO reusable caption component

const HeaderSubTitle = ({
	title,
	color,
	textAlign,
	level,
	fontSize,
	fontWeight,
}) => {
	const Title = `h${level || 1}`;

	return (
		<HeaderSubtitleStyled
			textAlign={textAlign}
			fontSize={fontSize}
			fontWeight={fontWeight}
			color={color}
		>
			<Title style={{ fontWeight: `${fontWeight}` }}>{title}</Title>
		</HeaderSubtitleStyled>
	);
};

export default HeaderSubTitle;
