import { HeroImageStyled } from "../styled-components";
const HeroImage = ({ img, alt, blockSize, inlineSize }) => {
	return (
		<HeroImageStyled
			src={img}
			alt={alt}
			blockSize={blockSize}
			inlineSize={inlineSize}
		/>
	);
};

export default HeroImage;
