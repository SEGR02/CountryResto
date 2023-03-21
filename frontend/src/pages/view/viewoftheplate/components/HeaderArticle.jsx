import { HeaderArticleStyled } from "../styled-components/layout.styled";
import start from "/assets/icons/Star.svg";
const HeaderArticle = ({ nameFood, qualification }) => {
	return (
		<HeaderArticleStyled>
			<h2>{nameFood}</h2>
			<div>
				<img src={start} alt='start' />
				<p>{qualification}</p>
			</div>
		</HeaderArticleStyled>
	);
};

export default HeaderArticle;
