This project adds a view called Repository View based on the Eclipse CNF (Common Navigator Framework).
It creates a view content providers that create the Talend repo view and a sample content provider to 
check external contribution works.

Using CNF is very handy because anyone can add new items and even enrich our own tree items.
user can add content, filter, sorter, actions, wizard using extension points.
The job content provider is relying on the old legacy content provider.


There is also an implementation of a filter that is parametrized using Extension points. This filter only displays the content specified by the extension point according to the perspective.
This mean a single view (Instead of have differents view implementations) could be used between different perspective and the content displayed could be filtered according to the current perspective.

