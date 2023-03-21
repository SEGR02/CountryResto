import { useEffect, useState } from "react";
import { useGetCategoriesQuery } from "../../../../redux/query/FoodInfo.query";
const useCategories = () => {
	const [categories, setCategories] = useState([]);

	const { data: cate, isSuccess } = useGetCategoriesQuery();

	useEffect(() => {
		if (isSuccess) {
			const { dishCategories } = cate._embedded;
			setCategories([...dishCategories]);
		}
	}, [isSuccess]);

	return {
		categories,
		isSuccess,
	};
};

export default useCategories;
