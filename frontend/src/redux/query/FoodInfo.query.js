import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

// const baseUrl = "http://localhost:8080/api";
const baseUrl =
  "https://c9-20-javareact-fulltime-production.up.railway.app/api";

export const FoodInfoApi = createApi({
  reducerPath: "food",
  baseQuery: fetchBaseQuery({ baseUrl }),
  endpoints: (builder) => ({
    getCategories: builder.query({
      query: () => "/categories",
    }),
    getDishes: builder.query({
      query: () => "/dishes?page=0&size=999",
    }),

    addNewPost: builder.mutation({
      query: (post) => ({
        url: `/comments?dishId=${post.id}`,
        method: "POST",
        body: post.content,
        headers: {
          "Content-Type": "application/json; charset=UTF-8",
          Authorization: `Bearer ${post.token}`,
        },
      }),
    }),

    getComments: builder.query({
      query: (id) => `/comments?dishId=${id}&page=0&size=999`,
    }),

    getUserInfo: builder.query({
      query: (id) => `/users/${id}`,
    }),

    /* 		getAddressInfo: builder.query({
			query: (token) => ({
				url: `/addressess`,
				headers: {
					Authorization: `Bearer ${token}`,
					"Content-Type": "application/json",
				},
			}),
		}), */

    addNewDataUser: builder.mutation({
      query: (id, field) => ({
        url: `/users/${id}`,
        method: "PUT",
        body: field,
        headers: {
          "Content-Type": "application/json",
        },
      }),
    }),

    addPayment: builder.mutation({
      query: (post, token) => ({
        url: "/payment_methods",
        method: "POST",
        body: post,
        headers: {
          "Content-Type": "application/json; charset=UTF-8",
          Authorization: `Bearer ${token}`,
        },
      }),
    }),
  }),
});

export const {
  useGetCategoriesQuery,
  useGetDishesQuery,
  useGetInfoFoodQuery,
  useAddNewPostMutation,
  useGetCommentsQuery,
  useGetUserInfoQuery,
  useGetAddressInfoQuery,
  useAddNewDataUserMutation,
  useAddPaymentMutation,
} = FoodInfoApi;
