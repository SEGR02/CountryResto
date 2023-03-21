import { Suspense, lazy } from "react";
import Loader from "../../components/Loader";

const Home = () => {
	document.title = "Country Resto";

	const MainContent = lazy(() => import("./components/MainContent"));

	return (
		<Suspense fallback={<Loader />}>
			<MainContent />
		</Suspense>
	);
};

export default Home;
