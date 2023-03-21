import { useMemo } from "react";
import StarIcon from "./StartIcon";
const RatingIcon = ({
	index,
	rating,
	hoverRating,
	onMouseEnter,
	onMouseLeave,
	onSaveRating,
}) => {
	const fill = useMemo(() => {
		if (hoverRating >= index) {
			return "rgba(255, 196, 81, 1)";
		} else if (!hoverRating && rating >= index) {
			return "rgba(255, 196, 81, 1)";
		}

		return "none";
	}, [rating, hoverRating, index]);

	const handleClick = () => onSaveRating(index);
	const handleMouseEnter = () => onMouseEnter(index);

	return (
		// rome-ignore lint/a11y/useKeyWithClickEvents: <explanation>
		<div
			onMouseEnter={handleMouseEnter}
			onMouseLeave={onMouseLeave}
			onClick={handleClick}
		>
			<StarIcon fill={fill} />
		</div>
	);
};

export default RatingIcon;
