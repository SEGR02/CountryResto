import { useEffect, useState } from "react";
import { useGetDishesQuery } from "../../../../redux/query/FoodInfo.query";

const useListFood = (idNameCategory) => {
	const [list, setList] = useState([]);
	const [isLoading, setIsLoading] = useState(false);
	const { data: dis, isSuccess } = useGetDishesQuery();
	console.log(dis);
	useEffect(() => {
		if (isSuccess) {
			const { dishes } = dis._embedded;
			const templete = dishes.filter(
				(element) =>
					element.category.name === idNameCategory &&
					element.hasPromotion === false,
			);

			setList([...templete]);
			setIsLoading(true);
		}
	}, [isSuccess]);

	return {
		list,
		isLoading,
	};
};

export default useListFood;
