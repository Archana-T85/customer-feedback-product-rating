import { useEffect, useState } from "react";
import API from "../services/api";

function ReviewList({ productId }) {

    const [reviews, setReviews] = useState([]);

    useEffect(() => {

        if (productId) {
            fetchReviews();
        }

    }, [productId]);

    const fetchReviews = () => {

        API.get(`/reviews/product/${productId}`)
            .then((response) => {
                setReviews(response.data);
            })
            .catch((error) => {
                console.log(error);
            });

    };

    return (

        <div style={{ marginTop: "20px" }}>

            <h2>Customer Reviews</h2>

            {reviews.length === 0 ? (

                <p>No Reviews Available</p>

            ) : (

                reviews.map((review) => (

                    <div
                        key={review.reviewId}
                        className="review-card"
                    >

                        <h3>⭐ {review.rating}/5</h3>

                        <p>
                            <strong>Comment : </strong>
                            {review.reviewComment || "No Comment"}
                        </p>

                        <p>
                            <strong>Review Date : </strong>
                            {new Date(review.reviewDate).toLocaleString()}
                        </p>

                    </div>

                ))

            )}

        </div>

    );

}

export default ReviewList;