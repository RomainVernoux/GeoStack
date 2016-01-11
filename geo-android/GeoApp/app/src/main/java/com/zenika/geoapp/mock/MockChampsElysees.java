package com.zenika.geoapp.mock;

import android.support.v4.util.Pair;

import com.zenika.geoapp.mock.base.MockRun;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock run on the Champs-Elysées.
 *
 * Created by Romain Vernoux (romain.vernoux@zenika.com) on 10/01/2016.
 */
public class MockChampsElysees implements MockRun {


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, Double>> getMockRunCoordinates() {
        List<Pair<Double, Double>> run = new ArrayList<>();
        run.add(new Pair<>(48.872558, 2.298898));
        run.add(new Pair<>(48.872729, 2.298353));
        run.add(new Pair<>(48.872881, 2.297854));
        run.add(new Pair<>(48.873093, 2.297205));
        run.add(new Pair<>(48.873308, 2.296534));
        run.add(new Pair<>(48.873511, 2.295824)); //
        run.add(new Pair<>(48.873771, 2.295903));
        run.add(new Pair<>(48.874089, 2.295753));
        run.add(new Pair<>(48.874262, 2.295485)); //
        run.add(new Pair<>(48.874580, 2.295705));
        run.add(new Pair<>(48.874890, 2.295920));
        run.add(new Pair<>(48.875116, 2.296076));
        return run;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getIntervalMillis() {
        return 2000L;
    }
}
