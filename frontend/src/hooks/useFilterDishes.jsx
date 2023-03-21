import { useEffect, useState } from "react";
import { useGetDishesQuery } from "../redux/query/FoodInfo.query";

const useFilterDishes = (customFiltration) => {
	const [list, setList] = useState([]);

	const { data: food, isSuccess } = useGetDishesQuery();

	useEffect(() => {
		if (isSuccess && food !== undefined) {
			const { dishes } = food._embedded;
			setList(customFiltration(dishes));
		}
	}, [food]);

	return {
		list,
		isSuccess,
	};
};

export default useFilterDishes;
