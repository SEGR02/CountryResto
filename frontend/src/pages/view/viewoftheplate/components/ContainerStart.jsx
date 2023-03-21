import { ContainerStartStyled } from "../styled-components/layout.styled";
import RatingIcon from "./RatingIcon";

const ContainerStart = ({
	pos,
	rating,
	hoverRating,
	setHoverRating,
	setRating,
}) => {
	const onMouseEnter = (index) => {
		setHoverRating(index);
	};
	const onMouseLeave = () => {
		setHoverRating(0);
	};
	const onSaveRating = (index) => {
		setRating(index);
	};

	return (
		<ContainerStartStyled>
			<h2>Calificación</h2>
			<div>
				{pos.map((index) => (
					<RatingIcon
						key={index}
						index={index}
						rating={rating}
						hoverRating={hoverRating}
						onMouseEnter={onMouseEnter}
						onMouseLeave={onMouseLeave}
						onSaveRating={onSaveRating}
					/>
				))}
			</div>
		</ContainerStartStyled>
	);
};

export default ContainerStart;
