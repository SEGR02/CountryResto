import { lazy, Suspense } from "react";
import { Loader } from "../../../components";
const Category = () => {
	document.title = "Carta";
	const ContentCategory = lazy(() => import("./components/ContentCategory"));
	return (
		<Suspense fallback={<Loader />}>
			<ContentCategory />
		</Suspense>
	);
};

export default Category;
