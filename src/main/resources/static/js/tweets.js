const overlay = document.querySelector(".overlay");
const myModal = document.querySelector(".myModal");
const newTweetBtn = document.getElementById("newTweet");

// NEW TWEET FORM

newTweetBtn.addEventListener("click", () => {
  overlay.classList.remove("hidden");
  myModal.classList.remove("hidden");
  myModal.classList.add("showModal");
});
overlay.addEventListener("click", () => {
  overlay.classList.add("hidden");
  myModal.classList.add("hidden");
  myModal.classList.remove("showModal");
});
