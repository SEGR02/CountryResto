import { useEffect, useState } from "react";

const useStart = (position) => {
	const [pos, setPos] = useState([]);
	const [rating, setRating] = useState(0);
	const [hoverRating, setHoverRating] = useState(0);

	useEffect(() => {
		const template = [];
		for (let i = 1; i <= position; i++) {
			template.push(i);
		}
		setPos([...template]);
	}, [position]);

	return { pos, rating, setRating, hoverRating, setHoverRating };
};

export default useStart;
