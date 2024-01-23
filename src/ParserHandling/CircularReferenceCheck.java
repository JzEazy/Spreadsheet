package ParserHandling;

import SheetHandling.CellDataTypes.Coordinate;
import SheetHandling.SheetHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CircularReferenceCheck {

    SheetHandler mSheetHandler;
    public CircularReferenceCheck() {
        this.mSheetHandler = new SheetHandler();
    }

    public boolean isDependent(Coordinate coordinate, List<Coordinate> references) {
        Set<Coordinate> visited = new HashSet<>();
        return hasCircularDependency(coordinate, references, visited);
    }

    private boolean hasCircularDependency(Coordinate current, List<Coordinate> references, Set<Coordinate> visited) {
        // If we encounter the original coordinate in the recursion, there's a circular dependency
        if (visited.contains(current)) {
            return true;
        }

        visited.add(current);

        for (Coordinate ref : references) {
            // Assuming getReferences(ref) fetches the list of references for the Coordinate ref
            List<Coordinate> refReferences = mSheetHandler.getReferenceList(ref);

            if (refReferences != null && hasCircularDependency(current, refReferences, visited)) {
                return true;
            }
        }
        // Remove the current coordinate from visited as we backtrack
        visited.remove(current);
        return false;
    }
}
