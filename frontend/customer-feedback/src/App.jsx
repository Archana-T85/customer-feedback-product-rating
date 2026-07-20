import { useState } from "react";
import Navbar from "./components/Navbar";
import ProductList from "./components/ProductList";
import ReviewForm from "./components/ReviewForm";
import ReviewList from "./components/ReviewList";
import AverageRating from "./components/AverageRating";

function App() {

  const [selectedProduct, setSelectedProduct] = useState(null);
  const [userId, setUserId] = useState("");

  return (
    <div>

      

      <div className="container">
        <Navbar />

        <h2>Customer Feedback & Product Rating</h2>

        <label>User ID : </label>

        <input
          type="number"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
        />

        <ProductList
          setSelectedProduct={setSelectedProduct}
        />

        {selectedProduct && (
          <>
            <AverageRating productId={selectedProduct.productId} />

            <ReviewList productId={selectedProduct.productId} />

            <ReviewForm
              userId={userId}
              productId={selectedProduct.productId}
            />
          </>
        )}

      </div>

    </div>
  );
}

export default App;