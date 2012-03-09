This project adds a view called Repository View based on the Eclipse CNF (Common Navigator Framework).
It creates a view with an unused root and then creates a Job node with all jobs and a Business Model node with a sample of sub nodes.
Both container are created by 2 different content provider and there is also an example on how to create children of an existing node by creating a new content provider.
Using CNF is very handy because anyone can add new items and even enrich our own tree items.
The job content provider is relying on MDM implementation of a content provider just for the sake of having this example to be working without too many code.


There is also an implementation of a filter that is parametrized using Extension points. This filter only displays the content specified by the extension point according to the perspective.
This mean a single view (Instead of have differents view implementations) could be used between different perspective and the content displayed could be filtered according to the current perspective.

