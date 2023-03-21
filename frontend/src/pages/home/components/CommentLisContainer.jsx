import { Div } from "../../../styled-components";
import CommentsItems from "./CommentsItems";

const CommentLisContainer = () => {
	return (
		<>
			<Div gap={"30px"} ancho={"560px"}>
				<CommentsItems
					image={""}
					name={"Adriana"}
					comment={
						"Muy rico y abundante! Buena ambientación tradicional. Volvería a ir y lo recomiendo."
					}
					date={"21 de Mar. 2022 21:00hs"}
				/>
				<CommentsItems
					image={""}
					name={"Adrian"}
					comment={
						"Excelente lugar para venir a cenar en familia. Muy buena atención."
					}
					date={"10 de Jul. 2022 21:00hs"}
				/>
			</Div>

			<Div gap={"30px"} ancho={"560px"}>
				<CommentsItems
					image={""}
					name={"Carlos"}
					comment={
						"Festeje mi cumpleaños con amigos y la atención fue grandiosa. Los platos son abundantes y el clima muy cálido. Ver más"
					}
					date={"15 de Oct. 2022 23:00hs"}
				/>
				<CommentsItems
					image={""}
					name={"Fabio"}
					comment={"Muy rico. Volvería a ir y lo recomiendo."}
					date={"2 de Mar. 2022 18:00hs"}
				/>
			</Div>
		</>
	);
};

export default CommentLisContainer;
