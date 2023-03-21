import {
	DescriptionLoaderStyled,
	DivLoaderStyled,
	SpanLoaderStyled,
	ViewOfThePlateLoaderStyled,
} from "../styled-components/layout.styled";
const ViewOfThePlateLoader = () => {
	return (
		<ViewOfThePlateLoaderStyled>
			<DivLoaderStyled />
			<DescriptionLoaderStyled>
				<SpanLoaderStyled inlineSize='90%' blockSize='50px' />
				<SpanLoaderStyled inlineSize='42%' blockSize='30px' />
				<SpanLoaderStyled inlineSize='74%' blockSize='45px' />
				<SpanLoaderStyled inlineSize='27%' blockSize='46px' />
				<SpanLoaderStyled inlineSize='100%' blockSize='40px' />
			</DescriptionLoaderStyled>
		</ViewOfThePlateLoaderStyled>
	);
};

export default ViewOfThePlateLoader;
