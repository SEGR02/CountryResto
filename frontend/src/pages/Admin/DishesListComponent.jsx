import React from "react";

const DishesListComponent = ({ name }) => {
  return (
    <>
      <div className="items-container">
        <div className="item line">
          <p className="dish">{name.name}</p>
          <div className="item-icons">
            <svg
              width="22px"
              viewBox="0 0 18 18"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M10.5175 3.95143L14.1175 7.55143M1.51758 16.5514L5.88356 15.6717C6.11534 15.625 6.32816 15.5109 6.49529 15.3437L16.269 5.56461C16.7376 5.09576 16.7372 4.33577 16.2683 3.86731L14.1978 1.79923C13.729 1.33097 12.9694 1.33129 12.501 1.79995L2.72634 11.58C2.55953 11.7469 2.44563 11.9593 2.39888 12.1906L1.51758 16.5514Z"
                stroke="#49454F"
                stroke-width="2"
                stroke-linecap="round"
                stroke-linejoin="round"
              />
            </svg>
            <svg
              width="19"
              height="19"
              viewBox="0 0 19 19"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M1.06885 4L17.0688 4M13.0688 18H5.06885C3.96428 18 3.06885 17.1046 3.06885 16V5C3.06885 4.44772 3.51656 4 4.06885 4H14.0688C14.6211 4 15.0688 4.44772 15.0688 5V16C15.0688 17.1046 14.1734 18 13.0688 18ZM7.06885 4H11.0688C11.6211 4 12.0688 3.55229 12.0688 3V2C12.0688 1.44772 11.6211 1 11.0688 1H7.06885C6.51656 1 6.06885 1.44772 6.06885 2V3C6.06885 3.55229 6.51656 4 7.06885 4Z"
                stroke="#49454F"
                stroke-width="2"
                stroke-linecap="round"
              />
            </svg>
          </div>
        </div>
      </div>
    </>
  );
};

export default DishesListComponent;
