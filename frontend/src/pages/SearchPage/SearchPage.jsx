import { lazy, Suspense } from "react";
import { Loader } from "../../components";

const SearchPage = () => {
	document.title = "Bùsqueda";

	const ContentSearchPage = lazy(() => import("./components/ContentSearchPage"));
	return (
		<Suspense fallback={<Loader />}>
			<ContentSearchPage />
		</Suspense>
	);
};

export default SearchPage;