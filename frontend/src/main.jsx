import React from "react";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { RouterProvider } from "react-router-dom";
import store from "./redux/store";
import { router } from "./router/router";
import { GlobalStyle } from "./styled-components/global/rootStyles.styled";
ReactDOM.createRoot(document.getElementById("root")).render(
	<React.StrictMode>
		<Provider store={store}>
			<RouterProvider router={router} />
			<GlobalStyle />
		</Provider>
	</React.StrictMode>,
);
