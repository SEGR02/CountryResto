import { useState } from "react";

const useDisplacement = () => {
	const [move, setMove] = useState(14);

	const handleMoveRight = () => {
		if (move !== 14) {
			setMove(move + 28);
		}
	};
	const handleMoveLeft = () => {
		if (move !== -14) {
			setMove(move - 28);
		}
	};

	return {
		move,
		handleMoveRight,
		handleMoveLeft,
	};
};

export default useDisplacement;
