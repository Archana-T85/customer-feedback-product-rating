import { useEffect, useState } from "react";
import API from "../services/api";

function AverageRating({ productId }) {
  const [average, setAverage] = useState(0);

  useEffect(() => {
    if (productId) {
      API.get(`/reviews/product/${productId}/average`)
        .then((response) => {
          setAverage(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [productId]);

  return (
    <div className="average">
      <h3>Average Rating</h3>
      <h2>⭐ {Number(average).toFixed(1)} / 5</h2>
    </div>
  );
}

export default AverageRating;