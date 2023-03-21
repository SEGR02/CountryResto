import { HeaderSubTitle } from "../../../components";
import { Div } from "../../../styled-components";
import { ParagraphStyled } from "../styled-components/layout.styled";
import ItemsComments from "./itemsComments";
import persona1 from "/assets/persona1.png";
import persona2 from "/assets/persona2.png";
import persona3 from "/assets/persona3.png";
import persona4 from "/assets/persona4.png";

const CustomerFeedback = () => {
	return (
		<section>
			<HeaderSubTitle
				level={2}
				textAlign='center'
				title='Lo que dicen nuestros clientes'
				fontWeight={"500"}
				fontSize={"1.2rem"}
			/>
			<ParagraphStyled>
				Estamos muy orgullosos de ofrecer a nuestros clientes la mejor
				experiencia de Country Resto, y nos complace saber que nuestros
				esfuerzos son apreciados. Te invitamos a conocernos y a disfrutar de
				nuestros platos destacados en la ciudad de Buenos Aires.
			</ParagraphStyled>

			<Div gap='50px' margin=' 7rem 0 0 0'>
				<ItemsComments
					img={persona3}
					name={"Adriana"}
					comment={
						"Muy rico y abundante! Buena ambientación tradicional. Volvería a ir y lo recomiendo."
					}
					date={"21 de Mar. 2022 21:00hs "}
				/>
				<ItemsComments
					img={persona2}
					name={"Ezequiel"}
					comment={
						"Excelente lugar para venir a cenar en familia. Muy buena atención."
					}
					date={" 10 de Jul. 2022 21:00hs"}
				/>
				<ItemsComments
					img={persona1}
					name={"Carlos"}
					comment={
						" Festeje mi cumpleaños con amigos y la atención fue grandiosa."
					}
					date={"15 de Oct. 2022 23:00hs"}
				/>
				<ItemsComments
					img={persona4}
					name={"Fabio"}
					comment={"Muy rico. Volvería a ir y lo recomiendo."}
					date={"2 de Mar. 2022 18:00hs"}
				/>
			</Div>
		</section>
	);
};

export default CustomerFeedback;
