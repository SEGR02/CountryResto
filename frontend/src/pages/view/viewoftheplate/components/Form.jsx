import useComment from "../hook/useComment";
import useStart from "../hook/useStart";
import { FormStyled } from "../styled-components/layout.styled";
import ContainerStart from "./ContainerStart";
const Form = () => {
	const { handleChange, onSavePost, comment } = useComment();
	const { pos, rating, hoverRating, setHoverRating, setRating } = useStart(5);

	const handleSubmit = (event) => {
		onSavePost();
	};

	return (
		<>
			<ContainerStart
				pos={pos}
				rating={rating}
				hoverRating={hoverRating}
				setHoverRating={setHoverRating}
				setRating={setRating}
			/>
			<FormStyled onSubmit={handleSubmit}>
				<label htmlFor='text'>Déjanos tu comentario</label>
				<input
					type='text'
					placeholder="Deje su comentario aca"
					name='text'
					required
					onChange={handleChange}
					value={comment}
				/>
			</FormStyled>
		</>
	);
};

export default Form;
