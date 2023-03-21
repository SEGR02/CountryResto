import {
	ButtonLoaderStyled,
	ForPeopleStyled,
	HeaderLoaderStyled,
	ImageLoaderStyled,
	ItemLoaderStyled,
	ParagraphLoaderStyled,
	PrecisLoaderStyled,
	TitleLoaderStyled,
} from "../styled-components";

const ItemsLoader = ({
	borderRadius,
	inlineSize,
	blockSize,
	top,
	insetInline,
}) => {
	return (
		<ItemLoaderStyled>
			<HeaderLoaderStyled>
				<ImageLoaderStyled
					borderRadius={borderRadius}
					inlineSize={inlineSize}
					blockSize={blockSize}
					top={top}
					insetInline={insetInline}
				/>
			</HeaderLoaderStyled>
			<TitleLoaderStyled />
			<ParagraphLoaderStyled />
			<ForPeopleStyled />
			<PrecisLoaderStyled />
			<ButtonLoaderStyled />
		</ItemLoaderStyled>
	);
};

export default ItemsLoader;
