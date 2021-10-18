export const fetchComments = (url, el) => {
  fetch(url)
    .then((res) => res.json())
    .then((data) => {
      console.log(data);

      if (data.length === 0) {
        el.innerHTML =
          "<p class='text-muted'><small>This tweet does not have any comments yet.</small></p>";
      } else {
        el.innerHTML = buildCommentElement(data);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

export const addComment = (url, obj, el) => {
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(obj),
  })
    .then((res) => res.json())
    .then((data) => {
      console.log(data);
      //fetch after adding
      fetchComments(url, el);
    })
    .catch((err) => {
      console.log(err);
    });
};

const buildCommentElement = (data) => {
  const html = data
    .map((comment) => {
      return `<li class="list-group-item border-0">
              <div class="card border-0">
                <div class="card-body">
                  <h6 class="card-subtitle mb-2 text-muted">
                    @${comment.account.username}
                  </h6>
                  <p class="card-text">${comment.text}</p>
                  <h6
                    id="date"
                    class="card-subtitle mb-2 text-muted"
                  ><small>${comment.createdAt.split("T")[0]}
                  ${comment.createdAt.split("T")[1].substring(0, 5)}</small>
                    
                  </h6>
                </div>
              </div>
            </li>`;
    })
    .join(" ");

  return html;
};
